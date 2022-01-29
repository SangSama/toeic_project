import { IDetailsGrammarUser } from '@/shared/model/details-grammar-user.model';
import { IGrammarTopic } from '@/shared/model/grammar-topic.model';

export interface IGrammarUser {
  id?: number;
  userId?: number;
  score?: number | null;
  createdAt?: Date;
  updatedAt?: Date;
  detailsGrammarUsers?: IDetailsGrammarUser[] | null;
  grammarTopic?: IGrammarTopic | null;
}

export class GrammarUser implements IGrammarUser {
  constructor(
    public id?: number,
    public userId?: number,
    public score?: number | null,
    public createdAt?: Date,
    public updatedAt?: Date,
    public detailsGrammarUsers?: IDetailsGrammarUser[] | null,
    public grammarTopic?: IGrammarTopic | null
  ) {}
}
