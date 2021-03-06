package com.suraj.exception;

import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<CustomException> {
    private static Logger log = Logger.getLogger(ExceptionMapper.class);

    public ExceptionMapper() {
    }
    @Override
    public Response toResponse(CustomException daoException) {
        if (log.isDebugEnabled()) {
            log.debug("Mapping exception to Response....");
        }
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(daoException.getMessage());

        // return internal server error for DAO exceptions
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
