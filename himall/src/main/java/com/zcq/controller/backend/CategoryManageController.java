package com.zcq.controller.backend;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcq.common.Const;
import com.zcq.common.ResponseCode;
import com.zcq.common.ServerResponse;
import com.zcq.dao.CategoryMapper;
import com.zcq.pojo.Category;
import com.zcq.pojo.User;
import com.zcq.service.ICategoryService;
import com.zcq.service.IUserService;
import com.zcq.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zcq
 * @Date: 2019/4/24 20:44
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private CategoryMapper categoryMapper;
    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId",defaultValue = "0") int parentId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        //校验一下是否是管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //增加我们处理分类的逻辑
            return iCategoryService.addCategory(categoryName,parentId);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //更新categoryName
            return iCategoryService.updateCategoryName(categoryId,categoryName);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //查询子节点的category信息,并且不递归,保持平级
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

//    @RequestMapping("get_deep_category.do")
//    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //查询当前节点的id和递归子节点的id
//            0->10000->100000
            return iCategoryService.selectCategoryAndChildrenById(categoryId);

        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

    /**
     * 获取最顶层
     * @return
     */
    @ResponseBody
    @RequestMapping("getChild.do")
    public Object getChild(@RequestParam(value = "id",defaultValue = "0")Integer parentId,Integer page,Integer rows){
        if(parentId==0){
            int count=iCategoryService.count();
            CategoryPage categoryPage=new CategoryPage();
            PageHelper.startPage(page,rows);
            List<Category> list=iCategoryService.findTop(parentId);
            List<CategoryVo> categoryVoList=new ArrayList<>();
            for(Category category:list){
                CategoryVo categoryVo=new CategoryVo();
                BeanUtils.copyProperties(category,categoryVo);
                categoryVoList.add(categoryVo);
            }
            categoryPage.setRows(categoryVoList);
            categoryPage.setTotal(count);
            return categoryPage;
        }else {
            List<Category> list=iCategoryService.findTop(parentId);
            List<CategoryVo> categoryVoList=new ArrayList<>();
            for(Category category:list){
                CategoryVo categoryVo=new CategoryVo();
                BeanUtils.copyProperties(category,categoryVo);
                categoryVoList.add(categoryVo);
            }
            return categoryVoList;
        }

    }
    @RequestMapping("combotree.do")
    @ResponseBody
    public List<ComboTreeVo> ComboTree(@RequestParam(value = "id",defaultValue = "0") Integer parentId){
        return forComboTree(parentId);
    }

    public List<ComboTreeVo> forComboTree(Integer parentId){
        List<Category> list=categoryMapper.selectCategoryChildrenByParentId(parentId);
        List<ComboTreeVo> comboTreeVoList=new ArrayList<>();
        for(Category category:list){
            ComboTreeVo comboTreeVo=new ComboTreeVo();
            comboTreeVo.setId(category.getId());
            comboTreeVo.setText(category.getName());
            Integer newParentId=category.getId();
            if(categoryMapper.selectChildCount(newParentId)>0){
                comboTreeVo.setChildren(forComboTree(newParentId));
            }
            comboTreeVoList.add(comboTreeVo);
        }
        return comboTreeVoList;
    }

    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public List<String> get(@RequestParam(defaultValue = "0") Integer parentId ){

        List<CategoryDTO> list= test(parentId);
        ArrayList<String> arrayList=new ArrayList<>();
        for(CategoryDTO categoryDTO:list){
            arrayList.add(categoryDTO.getCategory().getName());
        }
        return arrayList;
    }

    public List<CategoryDTO> test(Integer parentId){
        List<Category> list=categoryMapper.selectCategoryChildrenByParentId(parentId);
        List<CategoryDTO> categoryDTOList=new ArrayList<>();
        for(Category category:list){
            CategoryDTO categoryDTO=new CategoryDTO();
            categoryDTO.setCategory(category);
            Integer newParentId=category.getId();
            if(categoryMapper.selectChildCount(newParentId)>0){
                categoryDTO.setChildren(test(newParentId));
            }
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }


    /**
     * 根据子id获得父分类
     * @param id
     * @return
     */
    @RequestMapping("get_parent.do")
    @ResponseBody
    public Category getCategoryByChildId(Integer id){
        //先获取子id获取他的父id
        Category category=categoryMapper.getParentById(id);
        if(category.getParentId()==0){
            return category;
        }else{
            Category parent=categoryMapper.getParentById(category.getParentId());
            return parent;
        }

    }

    /**
     * 更新
     */
    @RequestMapping("update_category.do")
    @ResponseBody
    public  void updateCategory(){

    }
}
