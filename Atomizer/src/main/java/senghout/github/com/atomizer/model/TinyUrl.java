package senghout.github.com.atomizer.model;

public class TinyUrl {

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
