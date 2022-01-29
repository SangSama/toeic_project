import { IGrammarAnswer } from '@/shared/model/grammar-answer.model';
import { IGrammarTopic } from '@/shared/model/grammar-topic.model';

export interface IGrammarQuestion {
  id?: number;
  question?: string;
  correct?: string;
  createdAt?: Date;
  updatedAt?: Date;
  grammarAnswer?: IGrammarAnswer | null;
  grammarTopic?: IGrammarTopic | null;
}

export class GrammarQuestion implements IGrammarQuestion {
  constructor(
    public id?: number,
    public question?: string,
    public correct?: string,
    public createdAt?: Date,
    public updatedAt?: Date,
    public grammarAnswer?: IGrammarAnswer | null,
    public grammarTopic?: IGrammarTopic | null
  ) {}
}
