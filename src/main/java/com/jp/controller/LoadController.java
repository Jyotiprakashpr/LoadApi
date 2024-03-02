package com.jp.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jp.bindings.LoadBinding;
import com.jp.entities.Load;
import com.jp.services.LoadService;

@RestController
public class LoadController {

	@Autowired
	private LoadService loadService;

	@GetMapping("/loads")
	public List<Load> getLoads() {
		return this.loadService.getLoads();
	}

	@GetMapping("/loads/{loadId}")
	public ResponseEntity<LoadBinding> getLoad(@PathVariable Long loadId) {
		LoadBinding loadVO = this.loadService.getLoadById(loadId);
		return ResponseEntity.ok(loadVO);
	}

	@PostMapping("/loads")
	public ResponseEntity<String> addLoad(@RequestBody LoadBinding loadBinding) {
		String response = this.loadService.addLoad(loadBinding);
		return ResponseEntity.ok(response);
	}
	/*
	 * @PostMapping("/loads") public String addLoads(@RequestBody Load load) {
	 * return this.loadService.addLoad(load); }
	 */

	@PutMapping("/loads/{loadId}")
	public ResponseEntity<String> updateLoad(@PathVariable Long loadId, @RequestBody Load  updatedLoad) {
		String response = this.loadService.updateLoad(loadId, updatedLoad);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/loads/{loadId}")
	public ResponseEntity<String> deleteLoad(@PathVariable Long loadId) {
		try {
			this.loadService.deleteLoad(loadId);
			return new ResponseEntity<>("Load deleted successfully", HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>("Load not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
