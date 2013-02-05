package messaging.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import messaging.Message;
import messaging.api.responses.PostMessageResponse;

import org.jboss.resteasy.annotations.cache.NoCache;

@Path("/1.0/messages")
@Consumes("application/json")
@Produces("application/json")
@NoCache
public interface MessagingAPI {

    @GET
    @Path("/")
    @Produces("text/plain")
    String getHelp();

    /**
     * Gets all messages since the given time.
     * 
     * @param since
     * @return a list of Message, or null if there aren't any, or there was a problem
     */
    @GET
    @Path("/{since}")
    List<Message> getMessages(@PathParam("since") long since);

    @DELETE
    @Path("/")
    Response clearMessages();

    @GET
    @Path("/stats")
    Response getStats();

    @GET
    @Path("/announce")
    Response announcePeer(@QueryParam("url") String url);

    @POST
    @Path("/")
    PostMessageResponse postMessage(Message message);

}
