package facebook.command

import groovy.json.JsonBuilder
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class UserInfo {

    private String id
    private String name

    UserInfo() {
    }

    String getId() {
        return id
    }

    void setId(String imageUrl) {
        this.id = imageUrl
    }

    String getName() {
        return name
    }

    void setName(String userName) {
        this.name = userName
    }

    @Override
    String toString() {
        JsonBuilder builder = new JsonBuilder(this)
        return builder.toPrettyString()
    }
}
