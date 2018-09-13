package cn.jiiiiiin.security.core.properties;

/**
 * @author jiiiiiin
 */
public class BrowserProperties {

    /**
     * session管理配置项
     */
    private SessionProperties session = new SessionProperties();

    /**
     * 身份认证（登录）页面
     * 默认页面："/signIn.html"
     */
    private String signInUrl = "/signIn.html";

    /**
     * 社交登录，如果需要用户注册，跳转的页面
     */
    private String signUpUrl = "/signUp.html";

    /**
     * 退出成功时跳转的html输出页面，如果是移动端渠道访问则返回json数据，不走该页面。
     */
    private String signOutUrl = "/signOut.html";

    /**
     * '记住我'功能的有效时间，单位（秒），默认1小时
     */
    private int rememberMeSeconds = 3600;

    /**
     * 代理地址，用于访问第三方授权服务标识自身应用的服务器地址
     */
    private String proxyUri;


    public String getSignInUrl() {
        return signInUrl;
    }

    public void setSignInUrl(String signInUrl) {
        this.signInUrl = signInUrl;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    public String getProxyUri() {
        return proxyUri;
    }

    public void setProxyUri(String proxyUri) {
        this.proxyUri = proxyUri;
    }
}