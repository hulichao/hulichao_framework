描述：这是一个原型是apache服务器，第一个版本实现了浏览器请求服务器响应返回请求内容。
原理：web应用从静态到动态的实现是应用了web服务器的动态返回，实现时候，应用了socket解析请求内容，然后将请求的内容返回
如果找不到请求内容或者请求错误，返回对应的错误码跟错误消息。
注意请求头的格式比如HTTP/1.0 不能写成 HTTP:1.0
请求文件的内容格式是webroot+filename 在这里 webroot 没有加结尾的//  在浏览器生成的页面上给filename 加了/ 也就是 实际路径是例如 f://imgage filename  而拼接成了f://image/filename，原因是301永久重定向请求。可参考文章http://blog.csdn.net/hu_lichao/article/details/79191070
