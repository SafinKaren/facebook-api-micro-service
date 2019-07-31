package facebook.props

import groovy.json.JsonBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "api")
class AppProperties {
    String facebookApiURL
    String accessToken

    String getFacebookApiURL() {
        return facebookApiURL
    }

    void setFacebookApiURL(String facebookApiURL) {
        this.facebookApiURL = facebookApiURL
    }

    String getAccessToken() {
        return accessToken
    }

    void setAccessToken(String accessToken) {
        this.accessToken = accessToken
    }

    @Override
    String toString() {
        JsonBuilder builder = new JsonBuilder(this)
        return builder.toPrettyString()
    }
}
