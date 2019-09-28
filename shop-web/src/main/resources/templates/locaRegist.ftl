<!DOCTYPE html>
<html>
<head>
<title>注册微信商城账户</title>
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
				<a href="javascript:void(0)"> <img id="logo-img"
												   src="./static/images/logo4.jpg"
												   width="80">
				</a>
			</div>

			<h1 class="nl-login-title" id="custom_display_256">
				<span id="message_LOGIN_TITLE">注册账户</span>
			</h1>
			<h2 class="nl-login-title lsrp-appname display-custom-hide"
				id="lsrp_appName"></h2>



			<div class="nl-phone-tip">
				<div id="message_LOGIN_PHONETIP">非大陆地区请在手机号码前面添加当地的国际代码前缀(如香港
					+852 台湾 +886)</div>
				<div id="select_country_code">帮助</div>
			</div>

			<div class="nl-frame-container">
				<div class="ng-form-area show-place" id="form-area">
					<form method="post" action="regist" id="miniLogin"
						onsubmit="return MiniLogin.validateForm();">
						<div class="shake-area" id="shake_area" style="z-index: 30;">
							<div class="enter-area display-custom-hide" id="revalidate_user">
								<p class="revalidate-user-name" id="revalidate_user_name"></p>
							</div>
							<div class="enter-area" id="enter_user">
								<input type="text" class="enter-item first-enter-item"
									data-rule="(^[\w.\-]+@(?:[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*\.)+[A-Za-z]{2,6}$)|(^1\d{10}$)|(^\d{3,}$)|(^\++\d{2,}$)"
									id="miniLogin_username" name="username" autocomplete="off"
									placeholder="用户名称"> <i class="placeholder hide"
									id="message_INPUT_IDENTITY">用户名称</i> <span class="error-tip"><em
									class="error-ico"></em><span class="error-msg"></span></span>
							</div>
							<div class="enter-area" id="enter_user">
								<input type="text" name="email"
									class="enter-item last-enter-item"
									data-rule="(^[\w.\-]+@(?:[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*\.)+[A-Za-z]{2,6}$)|(^1\d{10}$)|(^\d{3,}$)|(^\++\d{2,}$)"
									id="miniLogin_username" name="phone" autocomplete="off"
									placeholder="邮&nbsp;&nbsp;&nbsp;&nbsp;箱"> <i
									class="placeholder hide" id="message_INPUT_IDENTITY">邮&nbsp;&nbsp;&nbsp;&nbsp;箱</i>
								<span class="error-tip"><em class="error-ico"></em><span
									class="error-msg"></span></span>
							</div>
						</div>

						<div class="shake-area" id="shake_area" style="z-index: 30;">
							<div class="enter-area display-custom-hide" id="revalidate_user">
								<p class="revalidate-user-name" id="revalidate_user_name"></p>
							</div>
							<div class="enter-area" id="enter_user">
								<input type="text" name="phone"
									class="enter-item first-enter-item"
									data-rule="(^[\w.\-]+@(?:[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*\.)+[A-Za-z]{2,6}$)|(^1\d{10}$)|(^\d{3,}$)|(^\++\d{2,}$)"
									id="miniLogin_username" name="phone" autocomplete="off"
									placeholder="手机号码"> <i class="placeholder hide"
									id="message_INPUT_IDENTITY">手机号码</i> <span class="error-tip"><em
									class="error-ico"></em><span class="error-msg"></span></span>
							</div>
							<div class="enter-area" style="z-index: 20;">
								<input type="password" class="enter-item last-enter-item"
									id="miniLogin_pwd" name="password" autocomplete="off"
									data-rule="" placeholder="密码"> <i
									class="placeholder hide" id="message_INPUT_PASSWORD">密码</i> <span
									class="error-tip"><em class="error-ico"></em><span
									class="error-msg"></span></span>
							</div>
						</div>

						<#if error??>
						<div>
							<span id="message_LOGIN_TOO_MUCH" style="color: red">
								${error}</span>
						</div>
						</#if>



						<div class="miniLogin_forbidden" id="miniLogin_forzen">
							<div>
								<span id="message_LOGIN_FORZEN">此帐号已被冻结，暂时无法登录</span>
							</div>
						</div>
						<input class="button orange" type="submit"
							id="message_LOGIN_IMMEDIATELY" value="立即注册"> <a
							style="display: none" id="redirectLink" href="" target="_top"></a>
						<a style="display: none" id="redirectTwoPhraseLoginLink" href=""></a>

					</form>

				</div>
			</div>

			<div class="web-info">
				<p class="web-info-content" id="web_info_content"></p>
			</div>
		</div>



		<a target="_blank"></a>
</body>
</html>