export interface IExtendQuestion {
  id?: number;
  paragraph?: string | null;
  imageContentType?: string | null;
  image?: string | null;
  createdAt?: Date;
  updatedAt?: Date;
}

export class ExtendQuestion implements IExtendQuestion {
  constructor(
    public id?: number,
    public paragraph?: string | null,
    public imageContentType?: string | null,
    public image?: string | null,
    public createdAt?: Date,
    public updatedAt?: Date
  ) {}
}
