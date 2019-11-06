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
				<a href="javascript:void(0)"> <img id="logo-img"
					src="/images/1.png"
					width="80">
				</a>
                <img src="1.png">
			</div>

			<h1 class="nl-login-title" id="custom_display_256">
				<span id="message_LOGIN_TITLE">登录</span>
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
					<form method="post" action="login" id="miniLogin"
						onsubmit="return MiniLogin.validateForm();">
                         <input  type="hidden" name="source"
                          <#if source??>
                          value="${source}"
                          </#if>
                         >

                        <input type="hidden" name="token"
                                <#if token??>
                                    value="${token}"
                                </#if>
                        >
						<div class="shake-area" id="shake_area" style="z-index: 30;">
							<div class="enter-area display-custom-hide" id="revalidate_user">
								<p class="revalidate-user-name" id="revalidate_user_name"></p>
							</div>
							<div class="enter-area" id="enter_user">
								<input type="text" name="phone"
									class="enter-item first-enter-item"
									data-rule="(^[\w.\-]+@(?:[A-Za-z0-9]+(?:-[A-Za-z0-9]+)*\.)+[A-Za-z]{2,6}$)|(^1\d{10}$)|(^\d{3,}$)|(^\++\d{2,}$)"
									id="miniLogin_username" autocomplete="off"
									placeholder="邮箱/手机号码"> <i
									class="placeholder hide" id="message_INPUT_IDENTITY">手机号码</i> <span
									class="error-tip"><em class="error-ico"></em><span
									class="error-msg"></span></span>
							</div>
							<div class="enter-area" style="z-index: 20;">
								<input type="password" name="password"
									class="enter-item last-enter-item" id="miniLogin_pwd"
									autocomplete="off" data-rule="" placeholder="密码"> <i
									class="placeholder hide" id="message_INPUT_PASSWORD">密码</i> <span
									class="error-tip"><em class="error-ico"></em><span
									class="error-msg"></span></span>
							</div>
						</div>

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
						<#if error ??>
						<div>
							<span id="message_LOGIN_TOO_MUCH" style="color: red">${error}</span>
						</div>
						</#if>

						<div class="miniLogin_forbidden" id="miniLogin_forbidden">
							<div>
								<span id="message_LOGIN_TOO_MUCH">您的操作频率过快，请稍后再试。</span>(<span
									id="retryCountdown"></span>)
							</div>
						</div>

						<div class="miniLogin_forbidden">
							<div>
								<span id="message_LOGIN_FORZEN">此帐号已被冻结，暂时无法登录</span>
							</div>
						</div>
						<input class="button orange" type="submit"
							id="message_LOGIN_IMMEDIATELY" value="立即登录">

						<div class="ng-foot clearfix">

							<div style="display: none">
								<div class="ng-cookie-area" id="cookie_area">
									<input type="hidden" id="auto"><em class="checkbox"
										id="checkbox_item"></em> <span id="message_AUTOLOGIN_TWOWEEKS">两周内自动登录</span>
								</div>
							</div>

							<div class="ng-link-area">
								<span> <a
									href="qqauthorize"
									id="other_method_default">QQ登录</a><span> | </span>
								</span> <span id="custom_display_16"> <a
									href="javascript:void(0);" id="other_method">其他方式登录</a> <span>
										| </span>
								</span> <span id="custom_display_64"> <a
									href="https://account.xiaomi.com/pass/forgetPassword?callback=http%3A%2F%2Fm.mi.com%2Fmshopapi%2Fv1%2Fauthorize%2Fsso_callback%3Ffollowup%3Dhttp%253A%252F%252Fm.mi.com%252Findex.html%2523ac%253Daccount%2526op%253Dindex%26sign%3DYjJhY2VjZWEwZDYzOTNhNmZhOTRjYmRmMDVlN2ZlZTJhZDFhOTViOA%2C%2C&sid=mi_eshopm&_snsdefault=qq"
									id="message_FORGET_PASSWORD" target="_blank">忘记密码？</a>
								</span>
								<div class="third-area hide" id="third_area"></div>
							</div>

	> <		</div>

						<span id="custom_display_128"> <a
							href="locaRegister"
							class="button" id="message_REGISTER">注册帐号</a>
						</span> <span id="custom_display_8"> <a
							href="https://account.xiaomi.com/pass/sns/login/auth?appid=222161937813280&callback=http%3A%2F%2Fm.mi.com%2Fmshopapi%2Fv1%2Fauthorize%2Fsso_callback%3Ffollowup%3Dhttp%253A%252F%252Fm.mi.com%252Findex.html%2523ac%253Daccount%2526op%253Dindex%26sign%3DYjJhY2VjZWEwZDYzOTNhNmZhOTRjYmRmMDVlN2ZlZTJhZDFhOTViOA%2C%2C&sid=mi_eshopm"
							id="facebook_login_button" class="button facebook_area"> <i
								class="iconfacebook"></i>Facebook登录
						</a>
						</span> <a style="display: none" id="redirectLink" href="" target="_top"></a>
						<a style="display: none" id="redirectTwoPhraseLoginLink" href=""></a>

					</form>
					<div class="qrlogin-trigger" id="qrlogin-trigger">二维码登录</div>
				</div>
			</div>

			<div class="web-info">
				<p class="web-info-content" id="web_info_content"></p>
			</div>
		</div>

		<#include "common/bottom.ftl">
</body>
</html>