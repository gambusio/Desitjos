package com.github.gambusio.Desitjos.controllers;


import com.github.gambusio.Desitjos.entities.Item;
import com.github.gambusio.Desitjos.services.ItemService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;

@Component
@Path("/")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) { this.itemService = itemService;}

    @GET
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Item> listWishList() { return itemService.getItems();}

    @GET
    @Path("/items/{sUrl}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item listOneItem(@PathParam("sUrl") String sUrl) {return itemService.searchItem(sUrl);}

    @POST
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(Item item) {
        itemService.insertItem(item);
        return Response.created(URI.create("/items/" + item.getsUrl())).build();
    }

    @DELETE
    @Path("/items/{sUrl}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deteleItem(@PathParam("sUrl") String sUrl) {
        itemService.deleteItem(sUrl);
        return Response.ok().build();
    }
}