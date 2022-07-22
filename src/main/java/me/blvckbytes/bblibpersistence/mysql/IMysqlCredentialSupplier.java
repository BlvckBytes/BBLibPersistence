package me.blvckbytes.bblibpersistence.mysql;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 07/22/2022

  Implementations of this interface supply the persistence module with
  all required credentials in order to establish a database connection.
*/
public interface IMysqlCredentialSupplier {

  /**
   * Username to use for authentication
   */
  String getUsername();

  /**
   * Password to use for authentication
   */
  String getPassword();

  /**
   * Host to connect to
   */
  String getHost();

  /**
   * Port of the database service
   */
  int getPort();

  /**
   * Database to manipulate
   */
  String getDatabase();

}
