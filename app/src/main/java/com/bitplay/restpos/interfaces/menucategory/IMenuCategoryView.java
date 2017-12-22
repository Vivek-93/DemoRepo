package com.bitplay.restpos.interfaces.menucategory;

import com.bitplay.restpos.models.menucategory.MenuCategoryModel;
import com.bitplay.restpos.models.searchitem.SearchItemModel;
import com.bitplay.restpos.models.subcategory.SubcategoryModel;
import com.bitplay.restpos.models.subcategoryitem.SubCategoryItemModel;

/**
 * Created by Vivek on 19-12-2017.
 */

public interface IMenuCategoryView {

   void getMenuCategoryApiSuccess(int pid, MenuCategoryModel[] menuCategoryModel);
   void getMenuCategoryApiError(int pid, String errorData);

   void onGetSubCategorySuccess(int pid, SubcategoryModel[] subcategoryModels);
   void onGetSubCategoryError(int pid, String error);

   void onGetSubCategoryItemSuccess(int pid, SubCategoryItemModel[] subCategoryItemModel);
   void onGetSubCategoryItemError(int pid , String error);

   void onOrderBookedSuccess(int pid , String orderBookedSuccess);
   void onOrderBookedError(int pid , String orderBookedError);

}
