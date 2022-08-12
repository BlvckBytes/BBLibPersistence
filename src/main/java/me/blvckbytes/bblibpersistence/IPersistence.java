package me.blvckbytes.bblibpersistence;

import me.blvckbytes.bblibpersistence.exceptions.PersistenceException;
import me.blvckbytes.bblibpersistence.models.APersistentModel;
import me.blvckbytes.bblibpersistence.query.QueryBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/04/2022

  Represents the functionality a persistence implementation has to offer.

  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU Affero General Public License as published
  by the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Affero General Public License for more details.

  You should have received a copy of the GNU Affero General Public License
  along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
public interface IPersistence {

  /**
   * Store a model persistently
   * @param model Model to store
   */
  <T extends APersistentModel> void store(T model) throws PersistenceException;

  /**
   * Store a batch of models persistently (asynchronous)
   * @param models Batch of models
   * @param error Error callback, value is null on success
   */
  <T extends APersistentModel> void batchStore(List<T> models, Consumer<@Nullable PersistenceException> error);

  /**
   * List all available models of a certain type
   * @param type Type of model to list
   * @return List of all available records
   */
   <T extends APersistentModel> List<T> list(Class<T> type) throws PersistenceException;

  /**
   * Delete a previously created model
   * @param model Model to delete
   */
  <T extends APersistentModel> boolean delete(T model) throws PersistenceException;

  /**
   * Delete multiple previously created models
   * @param models Models to delete
   */
  <T extends APersistentModel> int delete(List<T> models) throws PersistenceException;

  /**
   * Delete models by a query
   * @param query Query that specifies what to delete
   */
  <T extends APersistentModel> int delete(QueryBuilder<T> query) throws PersistenceException;

  /**
   * Delete a previously created model by it's id
   * @param id ID of the model
   */
  <T extends APersistentModel>boolean delete(Class<T> type, UUID id) throws PersistenceException;

  /**
   * Find all models that match the specified query
   * @param query Query to execute
   * @return List of models
   */
  <T extends APersistentModel> List<T> find(QueryBuilder<T> query) throws PersistenceException;

  /**
   * Count all models that match the specified query
   * @param query Query to execute
   */
  <T extends APersistentModel> int count(QueryBuilder<T> query) throws PersistenceException;

  /**
   * Count all models of a specific type
   * @param type Type of model to count
   */
  <T extends APersistentModel> int count(Class<T> type) throws PersistenceException;

  /**
   * Find the first model that matches the specified query
   * @param query Query to execute
   * @return First model, empty if there were no matches
   */
  <T extends APersistentModel> Optional<T> findFirst(QueryBuilder<T> query) throws PersistenceException;

  /**
   * Get a set of properties for all models that match
   * the specified query in their raw, unwrapped form
   * @param query Query to execute
   * @param properties Properties to receive within the map
   * @return List of properties
   */
  <T extends APersistentModel> List<Map<String, Object>> findRaw(QueryBuilder<T> query, String... properties);

  /**
   * Get a set of properties for all models that are available
   * @param type Type of model to list
   * @param properties Properties to receive within the map
   * @return List of properties for all available items
   */
  <T extends APersistentModel> List<Map<String, Object>> listRaw(Class<T> type, String... properties);
}
