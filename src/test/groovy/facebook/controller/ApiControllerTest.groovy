package facebook.controller

import facebook.command.UserInfo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import static org.assertj.core.api.Assertions.*

@RunWith(SpringRunner.class)
@SpringBootTest
class ApiControllerTest {

    @Autowired
    private ApiController controller

    @Test
    void getNameTest() throws Exception {
        assertThat(controller.getName(4)).isEqualTo(new UserInfo(id: 4, name: 'Mark Zuckerberg').toString())
        assertThat(controller.getName(1).statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @Test
    void getPhotoTest() throws Exception {
        assertThat(controller.getPhoto(4).statusCode).isEqualTo(HttpStatus.OK)
        assertThat(controller.getPhoto(1).statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
