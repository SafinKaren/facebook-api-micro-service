package facebook.service

import facebook.command.UserInfo
import facebook.props.AppProperties
import groovyx.net.http.URIBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
@ConfigurationPropertiesBinding
class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class)

    @Autowired
    private AppProperties appProperties

    UserInfo getUserInfo(long id) {

        logger.info "Getting name for user with ID: $id"

        String accessToken = appProperties.accessToken
        String facebookApiURL = appProperties.facebookApiURL

        URIBuilder uriBuilder = new URIBuilder(facebookApiURL)
        uriBuilder.setPath("/$id")
        uriBuilder.addQueryParam("access_token", accessToken)
        String facebookGraphApiURL = uriBuilder.toString()

        logger.info "Calling: $facebookGraphApiURL"

        RestTemplate restTemplate = new RestTemplate()
        return restTemplate.getForObject(facebookGraphApiURL, UserInfo.class) as UserInfo
    }

    File getUserPhoto(long id) {

        logger.info "Getting profile photo for user with ID: $id"

        String facebookApiURL = appProperties.facebookApiURL

        URIBuilder uriBuilder = new URIBuilder(facebookApiURL)
        uriBuilder.setPath("/$id/picture")
        uriBuilder.addQueryParam("type", 'large')
        String facebookGraphApiURL = uriBuilder.toString()

        logger.info "Calling: $facebookGraphApiURL"

        RestTemplate restTemplate = new RestTemplate()
        File tempFile = File.createTempFile("photo", ".temp", null)
        FileOutputStream fos = new FileOutputStream(tempFile)
        fos.write(restTemplate.getForObject(facebookGraphApiURL, byte[].class) as byte[])
        return tempFile
    }

}
