import { IDetailsToeicUser } from '@/shared/model/details-toeic-user.model';
import { IToeics } from '@/shared/model/toeics.model';

export interface IToeicUser {
  id?: number;
  userId?: number;
  score?: number | null;
  reading?: string;
  listening?: string;
  createdAt?: Date;
  updatedAt?: Date;
  detailsToeicUsers?: IDetailsToeicUser[] | null;
  toeics?: IToeics | null;
}

export class ToeicUser implements IToeicUser {
  constructor(
    public id?: number,
    public userId?: number,
    public score?: number | null,
    public reading?: string,
    public listening?: string,
    public createdAt?: Date,
    public updatedAt?: Date,
    public detailsToeicUsers?: IDetailsToeicUser[] | null,
    public toeics?: IToeics | null
  ) {}
}
