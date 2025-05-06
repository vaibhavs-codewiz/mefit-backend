package com.sp.utility;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SearchResponse<T extends SearchResponse.SearchResult>{

	public interface SearchResult extends Serializable
	{
		
	}

}
