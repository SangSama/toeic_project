import { IGrammarUser } from '@/shared/model/grammar-user.model';
import { IGrammarQuestion } from '@/shared/model/grammar-question.model';

export interface IGrammarTopic {
  id?: number;
  nameTopic?: string;
  description?: string | null;
  view?: number;
  test?: number;
  level?: number | null;
  filePractice?: string | null;
  createdAt?: Date;
  updatedAt?: Date;
  grammarUsers?: IGrammarUser[] | null;
  grammarQuestions?: IGrammarQuestion[] | null;
}

export class GrammarTopic implements IGrammarTopic {
  constructor(
    public id?: number,
    public nameTopic?: string,
    public description?: string | null,
    public view?: number,
    public test?: number,
    public level?: number | null,
    public filePractice?: string | null,
    public createdAt?: Date,
    public updatedAt?: Date,
    public grammarUsers?: IGrammarUser[] | null,
    public grammarQuestions?: IGrammarQuestion[] | null
  ) {}
}
