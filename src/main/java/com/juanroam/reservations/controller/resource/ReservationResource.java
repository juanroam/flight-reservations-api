package com.juanroam.reservations.controller.resource;

import com.juanroam.reservations.dto.ErrorDTO;
import com.juanroam.reservations.dto.ReservationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Reservations", description = "Operations related with reservation entity")
public interface ReservationResource {

    @Operation(description = "Get the information of all the reservations", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return the information of all the reservations",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500",
                    description = "Something bad happens obtaining the reservations",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),
    })
    ResponseEntity<List<ReservationDTO>> getReservations();

    @Operation(description = "Get the information about a reservation", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return the information of one reservation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class))),

            @ApiResponse(responseCode = "404",
                    description = "Reservation not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),

            @ApiResponse(responseCode = "500",
                    description = "Something bad happens obtaining the reservations",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "ID of the reservation to search", example = "1")
            }
    )
    ResponseEntity<ReservationDTO> getReservationById(@Min(1) @PathVariable Long id);

    @Operation(description = "Create one reservation", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return the created reservation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class))),

            @ApiResponse(responseCode = "400",
                    description = "Bad request of the information to persist",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),

            @ApiResponse(responseCode = "500",
                    description = "Something bad happens obtaining the reservations",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class)))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples =
            @ExampleObject(name = "Reservation", summary = "Example reservation to create", value = "{\n" +
                    "    \"passengers\": [\n" +
                    "        {\n" +
                    "            \"firstName\": \"JUAN\",\n" +
                    "            \"lastName\": \"RODRIGUEZ\",\n" +
                    "            \"documentNumber\": \"1555333444\",\n" +
                    "            \"documentType\": \"CC\",\n" +
                    "            \"birthday\": \"1991-01-01\"\n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"itinerary\": {\n" +
                    "        \"segment\": [\n" +
                    "            {\n" +
                    "                \"origin\": \"BOG\",\n" +
                    "                \"destination\": \"MDE\",\n" +
                    "                \"departure\": \"2025-06-15\",\n" +
                    "                \"arrival\": \"2025-06-20\",\n" +
                    "                \"carrier\": \"AA\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"price\": {\n" +
                    "            \"totalPrice\": 160,\n" +
                    "            \"totalTax\": 0,\n" +
                    "            \"basePrice\": 160\n" +
                    "        }\n" +
                    "    }\n" +
                    "}"))))
    ResponseEntity<ReservationDTO> save(@Valid @RequestBody ReservationDTO reservation);

    @Operation(description = "Update a reservation", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return the updated reservation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReservationDTO.class))),

            @ApiResponse(responseCode = "400",
                    description = "Bad request of the information to persist",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),

            @ApiResponse(responseCode = "404",
                    description = "Reservation not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),

            @ApiResponse(responseCode = "500",
                    description = "Something bad happens to obtain th reservations",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Id of the reservation to update", example = "1")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples =
            @ExampleObject(name = "Reservation", summary = "Example reservation to create", value = "{\n" +
                    "  \"id\": 1,\n" +
                    "  \"passengers\": [\n" +
                    "    {\n" +
                    "      \"firstName\": \"JUAN\",\n" +
                    "      \"lastName\": \"RODRIGUEZ\",\n" +
                    "      \"documentNumber\": \"1555333444\",\n" +
                    "      \"documentType\": \"CC\",\n" +
                    "      \"birthday\": \"1991-01-01\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"itinerary\": {\n" +
                    "    \"segment\": [\n" +
                    "      {\n" +
                    "        \"origin\": \"BOG\",\n" +
                    "        \"destination\": \"MDE\",\n" +
                    "        \"departure\": \"2025-06-15\",\n" +
                    "        \"arrival\": \"2025-06-21\",\n" +
                    "        \"carrier\": \"AA\"\n" +
                    "      }\n" +
                    "    ],\n" +
                    "    \"price\": {\n" +
                    "      \"totalPrice\": 160,\n" +
                    "      \"totalTax\": 0,\n" +
                    "      \"basePrice\": 160\n" +
                    "    }\n" +
                    "  }\n" +
                    "}"))))
    ResponseEntity<ReservationDTO> update(@Min(1) @PathVariable Long id, @Valid @RequestBody ReservationDTO reservation);

    @Operation(description = "Delete a reservation", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Return nothing",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))),

            @ApiResponse(responseCode = "404",
                    description = "Reservation not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class))),

            @ApiResponse(responseCode = "500",
                    description = "Something bad happens to obtain th reservations",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDTO.class)))},
            parameters = {
                    @Parameter(in = ParameterIn.PATH, name = "id", description = "ID of the reservation to delete", example = "1")
            })
    ResponseEntity<Void> delete(@Min(1) @PathVariable Long id);
}
