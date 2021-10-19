package com.ning.netty.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ning.netty.http.pojo.User;
import com.ning.netty.http.serialize.impl.JSONSerializer;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.AsciiString;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 接收处理http请求
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0-SNAPSHOT
 */
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    private static final Logger logger = LoggerFactory.getLogger(NettyHttpServerHandler.class);

    private HttpHeaders headers;
    private FullHttpRequest fullRequest;

    private static final String FAVICON_ICO = "/favicon.ico";
    private static final AsciiString CONTENT_TYPE = AsciiString.cached("Content-Type");
    private static final AsciiString CONTENT_LENGTH = AsciiString.cached("Content-Length");
    private static final AsciiString CONNECTION = AsciiString.cached("Connection");
    private static final AsciiString KEEP_ALIVE = AsciiString.cached("keep-alive");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) {
        User user = new User();
        user.setUserName("Nicholas");
        user.setDate(new Date());
        if (httpObject instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) httpObject;
            headers = request.headers();
            String uri = request.uri();
            logger.info("http uri: " + uri);
            if (uri.equals(FAVICON_ICO)) {
                return;
            }
            HttpMethod method = request.method();
            if (method.equals(HttpMethod.GET)) {
                printQueryString(new QueryStringDecoder(uri, Charsets.toCharset(CharEncoding.UTF_8)));
                user.setMethod("get");
            } else if (method.equals(HttpMethod.POST)) {
                //POST请求,由于你需要从消息体中获取数据,因此有必要把httpObject转换成FullHttpRequest
                fullRequest = (FullHttpRequest) httpObject;
                //根据不同的Content_Type处理body数据
                dealWithContentType();
                user.setMethod("post");
            }

            JSONSerializer jsonSerializer = new JSONSerializer();
            byte[] content = jsonSerializer.serialize(user);

            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(content));
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());

            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if (!keepAlive) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(CONNECTION, KEEP_ALIVE);
                ctx.write(response);
            }
        }
    }

    private void printQueryString(QueryStringDecoder queryDecoder) {
        Map<String, List<String>> uriAttributes = queryDecoder.parameters();
        //此处仅打印请求参数（你可以根据业务需求自定义处理）
        for (Map.Entry<String, List<String>> attr : uriAttributes.entrySet()) {
            for (String attrVal : attr.getValue()) {
                logger.info(attr.getKey() + "=" + attrVal);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    /**
     * 简单处理常用几种 Content-Type 的 POST 内容（可自行扩展）
     */
    private void dealWithContentType() {
        String contentType = getContentType();
        //可以使用HttpJsonDecoder
        switch (contentType) {
            case "application/json": {
                String jsonStr = fullRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
                JSONObject obj = JSON.parseObject(jsonStr);
                for (Map.Entry<String, Object> item : obj.entrySet()) {
                    logger.info(item.getKey() + "=" + item.getValue().toString());
                }
                break;
            }
            case "application/x-www-form-urlencoded": {
                //方式一：使用 QueryStringDecoder
                String jsonStr = fullRequest.content().toString(Charsets.toCharset(CharEncoding.UTF_8));
                printQueryString(new QueryStringDecoder(jsonStr, false));
                break;
            }
            case "multipart/form-data":
                //TODO 用于文件上传
                break;
            default:
                //do nothing...
                break;
        }
    }

    private String getContentType() {
        String typeStr = headers.get("Content-Type");
        String[] list = typeStr.split(";");
        return list[0];
    }
}
