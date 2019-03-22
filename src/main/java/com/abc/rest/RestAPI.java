package com.abc.rest;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import RestModels.TestResponseModel;

@Path("/testservice")
public class RestAPI {
	@GET
	@Produces("application/json")
	public Response convertCtoF() {

		Double fahrenheit;
		Double celsius = 36.8;
		fahrenheit = ((celsius * 9) / 5) + 32;
		TestResponseModel res = new TestResponseModel();
		res.setId("1");
		res.setTempFarenh(fahrenheit);
		return Response.ok().entity(res).build();
	}

	@POST
	@Consumes("application/json")
	public Response testPost(TestResponseModel res) {
		res.getId();
		return Response.ok().entity(res).build();

	}

	@Path("/tempCel/{c}/id/{id}")
	@GET
	@Produces("application/xml")
	public Response convertCtoFfromInput(@PathParam("c") @Min(10) Double c, @PathParam("id") @Size(max=10) String id) {
		Double fahrenheit;
		Double celsius = c;
		fahrenheit = ((celsius * 9) / 5) + 32;
		TestResponseModel res = new TestResponseModel();
		res.setId(id);
		res.setTempFarenh(fahrenheit);

		return Response.ok().entity(res).build();
	}
}
