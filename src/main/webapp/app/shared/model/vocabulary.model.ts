import { IVocabularyTopic } from '@/shared/model/vocabulary-topic.model';

export interface IVocabulary {
  id?: number;
  word?: string;
  mean?: string;
  createdAt?: Date;
  updatedAt?: Date;
  vocabularyTopic?: IVocabularyTopic | null;
}

export class Vocabulary implements IVocabulary {
  constructor(
    public id?: number,
    public word?: string,
    public mean?: string,
    public createdAt?: Date,
    public updatedAt?: Date,
    public vocabularyTopic?: IVocabularyTopic | null
  ) {}
}
