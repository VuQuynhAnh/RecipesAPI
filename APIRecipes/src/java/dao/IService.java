/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author DELL
 * @param <entity>
 * @param <viewModel>
 * @param <type>
 */
public interface IService<entity, viewModel, type> {

    List<viewModel> getData();

    viewModel getDataById(type id);

    boolean insertData(entity t);

    boolean updateData(entity t);

    boolean deleteData(type id, int userId);
}
