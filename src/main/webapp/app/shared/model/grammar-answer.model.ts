export interface IGrammarAnswer {
  id?: number;
  answerA?: string;
  answerB?: string;
  answerC?: string;
  answerD?: string;
  createdAt?: Date;
  updatedAt?: Date;
}

export class GrammarAnswer implements IGrammarAnswer {
  constructor(
    public id?: number,
    public answerA?: string,
    public answerB?: string,
    public answerC?: string,
    public answerD?: string,
    public createdAt?: Date,
    public updatedAt?: Date
  ) {}
}
