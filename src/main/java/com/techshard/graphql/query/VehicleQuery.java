package com.techshard.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.techshard.graphql.dao.entity.Vehicle;
import com.techshard.graphql.service.VehicleService;

import org.checkerframework.checker.nullness.Opt;
import org.hibernate.type.SetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class VehicleQuery implements GraphQLQueryResolver {

    @Autowired
    private VehicleService vehicleService;

    public List<Vehicle> getVehicles(final int count) {
        return this.vehicleService.getAllVehicles(count);
    }

    public Optional<Vehicle> getVehicle(final int id) {
        
    	
    	//**************** Modified code to update retrieved data from database when no data exists *******************************
    	
    	Optional<Vehicle> vehicle = this.vehicleService.getVehicle(id);
    	
    	try
    	{    	
    		Vehicle retrievedVehicle = vehicle.get();
    	}
    	catch (NoSuchElementException ex)
    	{
    		Vehicle newVehicle = new Vehicle();
    		
        	newVehicle.setType("No");
        	newVehicle.setModelCode("Such Vehicle");
        	newVehicle.setBrandName("Exists");        	
        	newVehicle.setLaunchDate(LocalDate.now());
        		
        	return Optional.of(newVehicle);
    		
    	} 
    	
    	return vehicle;
    	
    	//******************End modified code ****************************************************************
      	
        
    }
}
