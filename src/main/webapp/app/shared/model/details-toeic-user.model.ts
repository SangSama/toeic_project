import { IToeicUser } from '@/shared/model/toeic-user.model';

export interface IDetailsToeicUser {
  id?: number;
  toeicId?: number;
  partId?: number;
  questionId?: number;
  status?: number;
  createdAt?: Date;
  updatedAt?: Date;
  toeicUser?: IToeicUser | null;
}

export class DetailsToeicUser implements IDetailsToeicUser {
  constructor(
    public id?: number,
    public toeicId?: number,
    public partId?: number,
    public questionId?: number,
    public status?: number,
    public createdAt?: Date,
    public updatedAt?: Date,
    public toeicUser?: IToeicUser | null
  ) {}
}
