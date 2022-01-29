export interface IQnA {
  id?: number;
  userId?: number;
  email?: string;
  status?: number;
  createdAt?: Date;
  updatedAt?: Date;
}

export class QnA implements IQnA {
  constructor(
    public id?: number,
    public userId?: number,
    public email?: string,
    public status?: number,
    public createdAt?: Date,
    public updatedAt?: Date
  ) {}
}
