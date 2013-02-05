package messaging.api.responses;

public class PostMessageResponse {

    /**
     * Will be null if there was no error
     */
    private String errorMessage;

    /**
     * Will be null if there was an error
     */
    private Long postId;

    public PostMessageResponse() {
        // For deserialisation
    }

    public PostMessageResponse(final long id) {
        this.postId = id;
    }

    public PostMessageResponse(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setPostId(final Long postId) {
        this.postId = postId;
    }

    public Long getPostId() {
        return this.postId;
    }

    @Override
    public String toString() {
        return "PostMessageResponse [postId=" + this.postId + ", errorMessage=" + this.errorMessage
                + "]";
    }

}
