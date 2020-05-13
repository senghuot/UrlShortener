package senghout.github.com.atomizer.model;

import org.springframework.data.annotation.Id;

public class TinyUrl {

    @Id
    public String Id;
    public String tinyUrl;
    public String fullUrl;

    public TinyUrl(final String tinyUrl, final String fullUrl) {
        this.tinyUrl = tinyUrl;
        this.fullUrl = fullUrl;
    }

    @Override
    public String toString() {
        return String.format(
                "TinyUrl[tinyUrl=%s, fullUrl='%s']",
                tinyUrl, fullUrl);
    }
}
