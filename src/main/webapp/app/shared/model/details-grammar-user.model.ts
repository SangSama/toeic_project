import { IGrammarUser } from '@/shared/model/grammar-user.model';

export interface IDetailsGrammarUser {
  id?: number;
  grammarQuestionId?: number;
  status?: number;
  createdAt?: Date;
  updatedAt?: Date;
  grammarUser?: IGrammarUser | null;
}

export class DetailsGrammarUser implements IDetailsGrammarUser {
  constructor(
    public id?: number,
    public grammarQuestionId?: number,
    public status?: number,
    public createdAt?: Date,
    public updatedAt?: Date,
    public grammarUser?: IGrammarUser | null
  ) {}
}
