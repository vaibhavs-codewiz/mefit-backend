package com.sp.service;

import com.sp.dto.GymReviewRequest;
import com.sp.utility.Response;

public interface ReviewService {

	Response saveReview(GymReviewRequest request);

}
