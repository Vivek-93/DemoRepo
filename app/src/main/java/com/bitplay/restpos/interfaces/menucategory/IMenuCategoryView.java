package com.bitplay.restpos.interfaces.menucategory;

import com.bitplay.restpos.models.menucategory.MenuCategoryModel;

/**
 * Created by Vivek on 19-12-2017.
 */

public interface IMenuCategoryView {

   void getMenuCategoryApiSuccess(int pid, MenuCategoryModel[] menuCategoryModel);

   void getMenuCategoryApiError(int pid, String errorData);
}
