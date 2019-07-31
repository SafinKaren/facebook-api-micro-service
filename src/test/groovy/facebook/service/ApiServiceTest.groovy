package facebook.service

import facebook.command.UserInfo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.HttpClientErrorException

import static org.assertj.core.api.Assertions.*

@RunWith(SpringRunner.class)
@SpringBootTest
class ApiServiceTest {

    @Autowired
    private ApiService service
    @Autowired
    private ApplicationContext applicationContext

    @Test
    void getUserInfoTest() throws Exception {
        assertThat(service.getUserInfo(4).toString()).isEqualTo(new UserInfo(id: 4, name: 'Mark Zuckerberg').toString())
        assertThatThrownBy({
            service.getUserInfo(1)
        }).isInstanceOf(HttpClientErrorException.class).hasMessageContaining("404 Not Found")
    }

    @Test
    void getUserPhotoTest() throws Exception {
        assertThat(service.getUserPhoto(4)).isNotNull()
        assertThatThrownBy({
            service.getUserPhoto(1)
        }).isInstanceOf(HttpClientErrorException.class).hasMessageContaining("404 Not Found")
    }
}
