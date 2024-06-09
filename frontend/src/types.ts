// types.ts
export interface PageVo {
    title: string;
    createTime: string;
    QuestionPageEntity: QuestionPageEntity[];
}

export interface QuestionPageEntity {
    id: number;
    content: string;
}

export interface ExamVo {
    pageId: number;
    name: string;
    description: string;
    startTime: string;
    endTime: string;
}
