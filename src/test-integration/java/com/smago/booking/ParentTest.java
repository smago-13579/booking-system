package com.smago.booking;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.ObjectMapper;

import javax.sql.DataSource;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.EMBEDDED;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase(provider = EMBEDDED, refresh = BEFORE_EACH_TEST_METHOD)
public abstract class ParentTest {
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected DataSource dataSource;
    protected final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    protected<T> MvcResult performPost(String url, T body) {
        return mvc.perform(
                        post(url)
                                .content(mapper.writeValueAsString(body))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andReturn();
    }

    @SneakyThrows
    protected<T> MvcResult performGet(String url) {
        return mvc.perform(get(url))
                .andDo(print())
                .andReturn();
    }
}
