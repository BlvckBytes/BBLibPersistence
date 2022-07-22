package me.blvckbytes.bblibpersistence.models;

/*
  Author: BlvckBytes <blvckbytes@gmail.com>
  Created On: 05/08/2022

  Specifies the results a ASequencedModel sequence sorting operation may return.
*/
public enum SequenceSortResult {
  SORTED,
  ID_INVALID,
  IDS_MISSING,
  MODEL_UNKNOWN
}
