package com.jp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.jp.bindings.LoadBinding;
import com.jp.entities.Load;
import com.jp.repository.LoadRepository;

@Service
public class LoadServiceImpl implements LoadService {

	@Autowired
	private LoadRepository loadRepository;

	@Override
	public String addLoad(LoadBinding loadBinding) {
		Long id = loadBinding.getId();
		Load load;

		if (!ObjectUtils.isEmpty(id)) {
			Optional<Load> optionalLoad = loadRepository.findById(id);

			if (optionalLoad.isPresent()) {
				load = optionalLoad.get();
			} else {
				// Handle the case where the optional is empty, for example, by creating a new
				// Load instance.
				load = new Load();
				load.setShipperId(UUID.randomUUID().toString());
			}
		} else {
			load = new Load();
			load.setShipperId(UUID.randomUUID().toString());
		}

		load.setComment(loadBinding.getComment());
		load.setDate(loadBinding.getDate());
		load.setLoadingPoint(loadBinding.getLoadingPoint());
		load.setNoOfTrucks(loadBinding.getNoOfTrucks());
		load.setProductType(loadBinding.getProductType());
		load.setTruckType(loadBinding.getTruckType());
		load.setUnloadingPoint(loadBinding.getUnloadingPoint());
		load.setWeight(loadBinding.getWeight());

		Load savedLoad = loadRepository.save(load);
		return "Load details added successfully  <id>" + savedLoad.getId() + " <shipperId> " + savedLoad.getShipperId();
	}

	@Override
	public List<LoadBinding> getLoadsByShipperId(String shipperId) {
		List<Load> loadList = loadRepository.findByShipperId(shipperId);
		List<LoadBinding> loadBindingList = new ArrayList<>();
		loadList.stream().forEach(load -> {
			LoadBinding loadBinding = new LoadBinding();
			BeanUtils.copyProperties(load, loadBinding);
			loadBindingList.add(loadBinding);
		});
		return loadBindingList;
	}

	@Override
	public LoadBinding getLoadById(Long loadId) {
		Optional<Load> optionalLoad = loadRepository.findById(loadId);
		LoadBinding loadBindingList = null;
		if (optionalLoad.isPresent()) {
			loadBindingList = new LoadBinding();
			BeanUtils.copyProperties(optionalLoad.get(), loadBindingList);
		}
		return loadBindingList;
	}

	@Override
	public String updateLoad(Long loadId, Load updatedLoad) {
		Optional<Load> existingLoad = loadRepository.findById(loadId);
		if (existingLoad.isPresent()) {
			Load loadToUpdate = existingLoad.get();
			BeanUtils.copyProperties(updatedLoad, loadToUpdate);
			loadRepository.save(loadToUpdate);
			return "Load details updated successfully";
		} else {
			return "Load not found";
		}
	}

	@Override
	public String deleteLoad(Long loadId) {
		if (loadRepository.existsById(loadId)) {
			loadRepository.deleteById(loadId);
			return "Load deleted successfully";
		} else {
			return "Load not found";
		}
	}

	@Override
	public List<Load> getLoads() {
		return loadRepository.findAll();
	}
}
