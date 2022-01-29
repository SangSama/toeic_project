import { IToeics } from '@/shared/model/toeics.model';

export interface IParts {
  id?: number;
  part?: number;
  description?: string;
  example?: string;
  fileLC?: string;
  view?: number;
  test?: number;
  createdAt?: Date;
  updatedAt?: Date;
  toeics?: IToeics | null;
}

export class Parts implements IParts {
  constructor(
    public id?: number,
    public part?: number,
    public description?: string,
    public example?: string,
    public fileLC?: string,
    public view?: number,
    public test?: number,
    public createdAt?: Date,
    public updatedAt?: Date,
    public toeics?: IToeics | null
  ) {}
}
