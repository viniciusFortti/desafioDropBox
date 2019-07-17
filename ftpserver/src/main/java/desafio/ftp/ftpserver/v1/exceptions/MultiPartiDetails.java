package desafio.ftp.ftpserver.v1.exceptions;


public class MultiPartiDetails extends ErrorDetails {

    public static final class Builder {
        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public MultiPartiDetails build() {
            MultiPartiDetails multiPartiDetails = new MultiPartiDetails();
            multiPartiDetails.setDeveloperMessage(developerMessage);
            multiPartiDetails.setTitle(title);
            multiPartiDetails.setDetail(detail);
            multiPartiDetails.setTimestamp(timestamp);
            multiPartiDetails.setStatus(status);
            return multiPartiDetails;
        }
    }
}
