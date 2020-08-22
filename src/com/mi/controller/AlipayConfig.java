package com.mi.controller;

public class AlipayConfig {
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

		// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
		public static String app_id = "2021000116687976";
		
		// 商户私钥，您的PKCS8格式RSA2私钥
	    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDTziW3jVCOqfF91gtEoskGo5WXCcXoXssS/G51MCeFyueaMymEnku3bb0QVyDjL2N3fpFTH+5Ugo0EuaOeSnHjjWskh1i+fAzSjMGkWIBVD3Hw/SyXBdpIfEIABkTJwP205RyfI+sCmt5rjcsYlsYjUYyriDykTKkrCYRnprJ4u6mTCHqUm9mjIZm9N0DUa+1lJjW/xjIYpmSGwumNIbSh3QoUNPDVi2jg/u+lhvXQM0rvytvFrXml3V7n/OnQEy2FBuTOFhbMZ37LUwz7QWp50yY4m44j41TWuBQavBTHnjAOYkmKj5jKxpSVSG5sbmao16EYxVzlsRm0L2VYcb0HAgMBAAECggEAMLQwnBqUsZmGfGchxDCihTJMjaSjq6FQlRcS10OxcuSLiTvGikZUJuPHG+DNITCecLXb+P9YsHosry943kdEFQ1J2+Z7k6G22Rug04e+StD4ZG/zNu8TmKNqEdNfMf182YHtqPJ7cefmyViboRXajfiMnkz/lNBD/i98ENGWLIifbSk3BsQ9VCWRebi0d7x4lIKKBgMmQO7t12uGQnIfg46O4yORD+yctkAyswBeHl4EIQTBxyB3oRrQZYrTmUlQ5SGM+KRbeZ024LBOFTIPSF48PDe0wPpykZMrEPElBcmkP+MKDMNEDDy3V8zSRow2J13wl8ytiAYalB5aC793QQKBgQDzfv/CCVMR/UjLXqKUUYbzWUh6ZJOISsIl6KjychYAnV6hLp2hyL9Ueh9u/Nb92xnLVGsTcEts7Qke7zWqJWBl1ZFFmEeSa6cIjHda0fwFXNcURfnQhhJ2AUoOk9UZdTN8VqHDJV3dO6oUuIM4Tz+/SyZCXP+1JsnE7VFobPHjFwKBgQDeropdj1b6zqtasoeFvRPJ4y3nPgcRsp+rmk6Y/vnd+1p09olo4SUmmQEfGNHsoZ8Lf10nKLxo5yETSfUPsUYgJewFtXYc23T+3Bt8y5mk8c+WPObUMrT8Hp2tZP3qUZcSjKZu9L/V3SwijxE2MVIt8oA1j3s6VcIEQLgJDBbrkQKBgErNV/qe7LMZEVTQlLdluERnyDk5To1PEgedStF4NMQplBmF/Yzm48gq4B8zc0R3G5x4KZDGLZWf1+515BGJ++wsyL0Ng+c6RQz+QehXm8l19OjYel8R4Pf5e2uxBIUWmSN4ZadSglxlaOGGsWEKD3ZPusgmU7Ot38wsb5bZ6REvAoGBAJd14GcCuAafy61FASjWEj+fZfmDFwvSkwgFTLCDvTBfZodnqM7NVJeMOlcMaryosaUAXDvmWUCA9N5Jz4V87lWvdXnskwnNuyYeUBPx5+9pCekGQ3rRgoh5yoXFy281T6wuPoX5swGkDgDzjqFtqpfV+Z50mTx0WCLMYmDE+FIBAoGAZEf+CHbvVVJys1WNxPGl+oGjLl3anXmN8uFB+uOT9y6Kuv8WX0mewz6hnd+1pu4Vkerw7qCuXOW4nPZI1Ue5tzMkcl5g60qxttNyQ9vUUr5fbf118SGOheluKLAgUDOpOFN4jJj25rt8MT2PPCmw5QqipQjHHw05vm5lbUx74LA=";
		
		// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA084lt41QjqnxfdYLRKLJBqOVlwnF6F7LEvxudTAnhcrnmjMphJ5Lt229EFcg4y9jd36RUx/uVIKNBLmjnkpx441rJIdYvnwM0ozBpFiAVQ9x8P0slwXaSHxCAAZEycD9tOUcnyPrAprea43LGJbGI1GMq4g8pEypKwmEZ6ayeLupkwh6lJvZoyGZvTdA1GvtZSY1v8YyGKZkhsLpjSG0od0KFDTw1Yto4P7vpYb10DNK78rbxa15pd1e5/zp0BMthQbkzhYWzGd+y1MM+0FqedMmOJuOI+NU1rgUGrwUx54wDmJJio+YysaUlUhubG5mqNehGMVc5bEZtC9lWHG9BwIDAQAB";

		// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

		// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

		// 签名方式
		public static String sign_type = "RSA2";
		
		// 字符编码格式
		public static String charset = "utf-8";
		
		// 支付宝网关
		public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
		
		// 支付宝网关
		public static String log_path = "C:\\";

		public static String getApp_id() {
			return app_id;
		}

		public static void setApp_id(String app_id) {
			AlipayConfig.app_id = app_id;
		}

		public static String getMerchant_private_key() {
			return merchant_private_key;
		}

		public static void setMerchant_private_key(String merchant_private_key) {
			AlipayConfig.merchant_private_key = merchant_private_key;
		}

		public static String getAlipay_public_key() {
			return alipay_public_key;
		}

		public static void setAlipay_public_key(String alipay_public_key) {
			AlipayConfig.alipay_public_key = alipay_public_key;
		}

		public static String getNotify_url() {
			return notify_url;
		}

		public static void setNotify_url(String notify_url) {
			AlipayConfig.notify_url = notify_url;
		}

		public static String getReturn_url() {
			return return_url;
		}

		public static void setReturn_url(String return_url) {
			AlipayConfig.return_url = return_url;
		}

		public static String getSign_type() {
			return sign_type;
		}

		public static void setSign_type(String sign_type) {
			AlipayConfig.sign_type = sign_type;
		}

		public static String getCharset() {
			return charset;
		}

		public static void setCharset(String charset) {
			AlipayConfig.charset = charset;
		}

		public static String getGatewayUrl() {
			return gatewayUrl;
		}

		public static void setGatewayUrl(String gatewayUrl) {
			AlipayConfig.gatewayUrl = gatewayUrl;
		}

		public static String getLog_path() {
			return log_path;
		}

		public static void setLog_path(String log_path) {
			AlipayConfig.log_path = log_path;
		}
		
}
