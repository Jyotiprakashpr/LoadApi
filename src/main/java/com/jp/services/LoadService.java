package com.jp.services;

import java.util.List;

import com.jp.bindings.LoadBinding;
import com.jp.entities.Load;

public interface LoadService {

	public String addLoad(LoadBinding load);

	public List<LoadBinding> getLoadsByShipperId(String shipperId);

	public LoadBinding getLoadById(Long loadId);

	public String updateLoad(Long loadId, Load updatedLoad);

	public String deleteLoad(Long loadId);

	public List<Load> getLoads();



}
