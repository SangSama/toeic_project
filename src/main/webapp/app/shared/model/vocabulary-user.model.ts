import { IDetailsVocabularyUser } from '@/shared/model/details-vocabulary-user.model';
import { IVocabularyTopic } from '@/shared/model/vocabulary-topic.model';

export interface IVocabularyUser {
  id?: number;
  userId?: number;
  score?: number | null;
  createdAt?: Date;
  updatedAt?: Date;
  detailsVocabularyUsers?: IDetailsVocabularyUser[] | null;
  vocabularyTopic?: IVocabularyTopic | null;
}

export class VocabularyUser implements IVocabularyUser {
  constructor(
    public id?: number,
    public userId?: number,
    public score?: number | null,
    public createdAt?: Date,
    public updatedAt?: Date,
    public detailsVocabularyUsers?: IDetailsVocabularyUser[] | null,
    public vocabularyTopic?: IVocabularyTopic | null
  ) {}
}
