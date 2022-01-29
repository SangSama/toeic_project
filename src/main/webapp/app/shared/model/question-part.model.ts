import { IAnswerQuestion } from '@/shared/model/answer-question.model';
import { IParts } from '@/shared/model/parts.model';
import { IExtendQuestion } from '@/shared/model/extend-question.model';

export interface IQuestionPart {
  id?: number;
  question?: string;
  correct?: string;
  createdAt?: Date;
  updatedAt?: Date;
  answerQuestion?: IAnswerQuestion | null;
  parts?: IParts | null;
  extendQuestion?: IExtendQuestion | null;
}

export class QuestionPart implements IQuestionPart {
  constructor(
    public id?: number,
    public question?: string,
    public correct?: string,
    public createdAt?: Date,
    public updatedAt?: Date,
    public answerQuestion?: IAnswerQuestion | null,
    public parts?: IParts | null,
    public extendQuestion?: IExtendQuestion | null
  ) {}
}
