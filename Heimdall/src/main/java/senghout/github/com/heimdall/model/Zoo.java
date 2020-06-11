package senghout.github.com.heimdall.model;

import org.springframework.data.annotation.Id;

public class Zoo {

    @Id
    public String Id;
    public long low;
    public long high;

    public Zoo(final long low, final long high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d) is used = %b", low, high);
    }
}
