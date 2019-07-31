package facebook.controller

import facebook.service.ApiService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClientException
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.nio.file.Files

@RestController
@RequestMapping('/api')
class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class)

    @Autowired
    private ApiService apiService

    @RequestMapping(value = '/name/{id}', method = RequestMethod.GET)
    def getName(@PathVariable('id') long id) {

        if (id) {

            try {
                apiService.getUserInfo(id).toString()
            } catch (RestClientException e) {
                logger.error('', e)
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            }

        } else {
            logger.error("Facebook ID mustn't be NULL")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }

    }

    @RequestMapping(value = "/photo/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<StreamingResponseBody> getPhoto(@PathVariable('id') long id) {

        if (id) {

            try {
                File photo = apiService.getUserPhoto(id)
                StreamingResponseBody responseBody = { outputStream ->
                    Files.copy(photo.toPath(), outputStream)
                }
                ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(responseBody)
            } catch (RestClientException e) {
                logger.error('', e)
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
            }

        } else {
            logger.error("Facebook ID mustn't be NULL")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }

    }
}