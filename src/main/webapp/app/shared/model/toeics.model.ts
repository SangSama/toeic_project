import { IToeicUser } from '@/shared/model/toeic-user.model';
import { IParts } from '@/shared/model/parts.model';

export interface IToeics {
  id?: number;
  nameToeic?: string;
  number?: number;
  description?: string | null;
  view?: number;
  test?: number;
  linkDetail?: string | null;
  createdAt?: Date;
  updatedAt?: Date;
  toeicUsers?: IToeicUser[] | null;
  parts?: IParts[] | null;
}

export class Toeics implements IToeics {
  constructor(
    public id?: number,
    public nameToeic?: string,
    public number?: number,
    public description?: string | null,
    public view?: number,
    public test?: number,
    public linkDetail?: string | null,
    public createdAt?: Date,
    public updatedAt?: Date,
    public toeicUsers?: IToeicUser[] | null,
    public parts?: IParts[] | null
  ) {}
}
