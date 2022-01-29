import { IVocabularyUser } from '@/shared/model/vocabulary-user.model';

export interface IDetailsVocabularyUser {
  id?: number;
  vocabularyId?: number;
  status?: number;
  createdAt?: Date;
  updatedAt?: Date;
  vocabularyUser?: IVocabularyUser | null;
}

export class DetailsVocabularyUser implements IDetailsVocabularyUser {
  constructor(
    public id?: number,
    public vocabularyId?: number,
    public status?: number,
    public createdAt?: Date,
    public updatedAt?: Date,
    public vocabularyUser?: IVocabularyUser | null
  ) {}
}
