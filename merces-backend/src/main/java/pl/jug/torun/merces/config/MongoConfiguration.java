package pl.jug.torun.merces.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
@PropertySource("classpath:config.properties")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;


    @Override
    protected String getDatabaseName() {
        return "merces";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(env.getProperty("mongo_url"), env.getProperty("mongo_port", Integer.class));
    }

    @Override
    protected UserCredentials getUserCredentials() {
        return new UserCredentials(env.getProperty("mongo_user"), env.getProperty("mongo_pass"));
    }
}
