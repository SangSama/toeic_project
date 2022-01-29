import { IVocabularyUser } from '@/shared/model/vocabulary-user.model';
import { IVocabulary } from '@/shared/model/vocabulary.model';

export interface IVocabularyTopic {
  id?: number;
  nameTopic?: string;
  description?: string | null;
  view?: number;
  test?: number;
  level?: number | null;
  createdAt?: Date;
  updatedAt?: Date;
  vocabularyUsers?: IVocabularyUser[] | null;
  vocabularies?: IVocabulary[] | null;
}

export class VocabularyTopic implements IVocabularyTopic {
  constructor(
    public id?: number,
    public nameTopic?: string,
    public description?: string | null,
    public view?: number,
    public test?: number,
    public level?: number | null,
    public createdAt?: Date,
    public updatedAt?: Date,
    public vocabularyUsers?: IVocabularyUser[] | null,
    public vocabularies?: IVocabulary[] | null
  ) {}
}
