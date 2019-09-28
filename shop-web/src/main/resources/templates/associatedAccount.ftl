<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<meta content="yes" name="apple-mobile-web-app-capable" />
<!-- ios系统的私有标签，它指定在web app状态下，ios设备中顶端的状态条的颜色 -->
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!-- 设备浏览网页时对数字不启用电话功能 -->
<meta content="telephone=no,email=no" name="format-detection" />
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
<link rel="stylesheet" href="css/common.css">
<!-- 自适应样式单 -->
<link rel="stylesheet" href="css/adaptive.css">
<link rel="stylesheet" href="css/login.css">
<body class="zh_CN">
	<div class="layout">
		<div class="nl-content">
			<div class="nl-logo-area" id="custom_display_1">
			</div>

			<h1 class="nl-login-title" id="custom_display_256">
				<span id="message_LOGIN_TITLE">QQ授权登录</span>
			</h1>
			<h2 class="nl-login-title lsrp-appname display-custom-hide"
				id="lsrp_appName"></h2>

			<div id="custom_display_2">

			</div>

			<div class="nl-phone-tip">
				<div id="message_LOGIN_PHONETIP">非大陆地区请在手机号码前面添加当地的国际代码前缀(如香港
					+852 台湾 +886)</div>
				<div id="select_country_code">帮助</div>
			</div>

			<div class="nl-frame-container">
				<div class="ng-form-area show-place" id="form-area">
			

						<div class="enter-area img-code-area" id="img_code_area"
							style="display: none;">
							<input type="text" class="enter-item code-enter-item"
								id="miniLogin_captCode" autocomplete="off" maxlength="12"
								placeholder="验证码"> <img src="" class="code-img"
								id="code_img"> <i class="placeholder hide"
								id="message_INPUT_CONFIRM">验证码</i> <span class="error-tip"><em
								class="error-ico"></em><span class="error-msg"
								id="code_error_tips"></span></span>
						</div>

						<div class="miniLogin_forbidden" id="miniLogin_forbidden">
							<div>
								<span id="message_LOGIN_TOO_MUCH">您的操作频率过快，请稍后再试。</span>(<span
									id="retryCountdown"></span>)
							</div>
						</div>

						<div class="miniLogin_forbidden" id="miniLogin_forzen">
							<div>
								<span id="message_LOGIN_FORZEN">此帐号已被冻结，暂时无法登录</span>
							</div>
						</div>
						<input class="button orange" type="submit"
							id="message_LOGIN_IMMEDIATELY" value="关联新账户" onclick="javascript:location.href='locaRegister?source=qq'">

					

						<span id="custom_display_128" onclick="javascript:location.href='locaLogin?source=qq'"> <a href="#"
							class="button" id="message_REGISTER">关联已有账号</a>
						</span>  <a style="display: none" id="redirectLink" href="" target="_top"></a>
						<a style="display: none" id="redirectTwoPhraseLoginLink" href=""></a>

				</div>
			</div>

			<div class="web-info">
				<p class="web-info-content" id="web_info_content"></p>
			</div>
		</div>

		<#include "common/bottom.ftl">
</body>
</html>